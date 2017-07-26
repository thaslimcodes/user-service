package com.ysg.PostMan;


import com.ysg.resource.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by thaslim on 16/05/17.
 */
public class GenReq {

    public static String folderName = "/Users/thaslim/Projects/user-services/src/main/java/com/ysg/resource";
    private static String collectionName = "DCO";

    public static Class getClassName(String fileName) {
        Class c = null;
        switch (fileName) {
            case "AppResource":
                c=AppResource.class;
                break;
//            case "CityResource":
//                c=CityResource.class;
//                break;
//            case "RoleResource":
//                c=RoleResource.class;
//                break;
//            case "UserCityResource":
//                c=UserCityResource.class;
//                break;
//            case "UserResource":
//                c=UserResource.class;
//                break;
//            case "UserRoleResource":
//                c=UserRoleResource.class;
//                break;
        }
        return c;
    }

    public static void main(String[] args) {
        //getClassSwitch();
        getPostManRequest();
    }

    public static void getPostManRequest() {
        try {
            File file = new File("/Users/thaslim/Desktop/setupScripts.json");
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            PrintStream outp = new PrintStream(out);


            List<PostManData> pmdList = getRequestInfo();

            String componentId = UUID.randomUUID().toString();

            List<String> uuidList = new ArrayList<String>();
            for (PostManData pmd : pmdList) {

                String uuid = UUID.randomUUID().toString();
                uuidList.add(uuid);

                pmd.setComponentId(componentId);
                pmd.setRequestId(uuid);
                getRequests(pmd);
            }

            outp.println(getJSONHeader(componentId, uuidList));

            String reqStr = pmdList.stream()
                    .map(str -> str.getRequestString())
                    .collect(Collectors.joining(",")).toString();

            outp.println(reqStr);
            outp.println("\n]\n}");
            outp.close();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getRequests(PostManData pmd) {
        StringBuffer requests = new StringBuffer();
        requests.append("\n{");
        requests.append("\n\"id\": \"" + pmd.getRequestId() + "\",");
        requests.append("\n\"headers\": \"Content-Type: App/json\",");
        requests.append("\n\"url\": \"" + pmd.getUrl() + "\",");
        requests.append("\n\"preRequestScript\": null,");
        requests.append("\n\"pathVariables\": {},");
        requests.append("\n\"method\": \"" + pmd.getHttpMethod() + "\",");
        requests.append("\n\"data\": [],");
        requests.append("\n\"dataMode\": \"raw\",");
        requests.append("\n\"version\": 2,");
        requests.append("\n\"tests\": null,");
        requests.append("\n\"currentHelper\": \"normal\",");
        requests.append("\n\"helperAttributes\": {},");
        requests.append("\n\"time\": 1495480489654,");
        requests.append("\n\"name\": \"" + pmd.getHttpMethod() + " " + pmd.getName() + "\",");
        requests.append("\n\"description\": \"\",");
        requests.append("\n\"collectionId\": \"" + pmd.getComponentId() + "\",");
        requests.append("\n\"responses\": [],");
        if (pmd.getJsonString() != null) {
            requests.append("\n\"rawModeData\": \"" + pmd.getJsonString() + "\"");
        } else {
            requests.append("\n\"rawModeData\": \"\"");
        }
        requests.append("\n}");
        pmd.setRequestString(requests.toString());
    }

    private static String getJSONHeader(String componentId, List<String> uuidList) {
        StringBuffer jsonHeader = new StringBuffer();
        jsonHeader.append("\n{");
        jsonHeader.append("\n\"id\": \"" + componentId + "\",");
        jsonHeader.append("\n\"name\": \"" + collectionName + "\",");
        jsonHeader.append("\n\"description\": \"\",");
        jsonHeader.append("\n\"order\": [");
        String uuidStr = uuidList.stream()
                .map(str -> "\n\"" + str + "\"")
                .collect(Collectors.joining(",")).toString();
        jsonHeader.append(uuidStr);
        jsonHeader.append("\n],");
        jsonHeader.append("\n\"folders\": [],");
        jsonHeader.append("\n\"timestamp\": " + new Date().getTime() + ",");
        jsonHeader.append("\n\"owner\": \"1996513\",");
        jsonHeader.append("\n\"public\": false,");
        jsonHeader.append("\n\"requests\": [");
        return jsonHeader.toString();

    }

    public static List<PostManData> getRequestInfo() {
        List<PostManData> pmdList = new ArrayList<PostManData>();
        try {

            String packageName = "com.ysg.resource.";


            File folder = new File(folderName);
            File[] listOfFiles = folder.listFiles();


            for (int i = 0; i < listOfFiles.length; i++) {
                String fileName = listOfFiles[i].getName();
                fileName = fileName.substring(0, fileName.length() - 5);
                System.out.println("\n//////////////////");


                String rootUrl = "http://localhost:8080/";
                String url = null;
                Class c = getClassName(fileName);
                //GET URL
                if (c != null) {
                    for (Annotation an : c.getAnnotations()) {
                        if (an instanceof RequestMapping) {
                            RequestMapping rm = (RequestMapping) an;
                            String[] values = rm.value();
                            for (String value : values) {
                                url = rootUrl + value;
                            }
                        }
                    }


                    int y = 0;
                    Method[] mm = c.getMethods();

                    for (int j = 0; j < mm.length; j++) {
                        PostManData pmd = null;

                        for (Annotation an : mm[j].getDeclaredAnnotations()) {
                            if (an instanceof RequestMapping) {
                                RequestMapping rm = (RequestMapping) an;
                                RequestMethod[] methods = rm.method();
                                String[] values = rm.value();
//HTTP METHOD
                                String value = "";
                                if (values.length > 0)
                                    value = values[0].toString();
                                String httpMethod = null;
                                if (methods.length > 0) {
                                    httpMethod = methods[0].toString();
                                    pmd = new PostManData();
                                    System.out.print(httpMethod + ": " + url + value);
                                    pmd.setUrl(url + value);
                                    pmd.setName((url + value).replace(rootUrl, ""));
                                    pmd.setHttpMethod(httpMethod);

                                }
                                boolean firstReqParam = true;
                                if (mm[j].getParameterTypes().length > 0) {
                                    Class c1 = mm[j].getParameterTypes()[0];
//REQUEST PARAM
                                    for (Annotation[] an1 : mm[j].getParameterAnnotations()) {
                                        for (Annotation an2 : an1) {
                                            String className = an2.toString();
                                            if ((className).contains("RequestParam")) {
                                                if (firstReqParam) {
                                                    System.out.print("?");
                                                    pmd.setUrl(pmd.getUrl() + "?");
                                                    firstReqParam = false;
                                                } else {
                                                    System.out.print("&");
                                                    pmd.setUrl(pmd.getUrl() + "&");
                                                }
                                                RequestParam rp = (RequestParam) an2;
                                                String reqParamVal = rp.value();
                                                System.out.print(reqParamVal + "={" + reqParamVal + "}");
                                                pmd.setUrl(pmd.getUrl() + reqParamVal + "={" + reqParamVal + "}");
                                            }
                                        }
                                    }
                                    System.out.println();

                                    if (!(c1.getName().equalsIgnoreCase("java.lang.String") || c1.isPrimitive())) {
                                        if (c1 != null) {
                                            System.out.print("{");
                                            boolean firstElement = true;
                                            StringBuffer jsonString = new StringBuffer();
                                            for (Field fld : c1.getDeclaredFields()) {
                                                Class c2 = fld.getType();

                                                if (httpMethod.equalsIgnoreCase("POST") && firstElement) {
                                                    firstElement = false;
                                                } else {
                                                    getJSONString(fld, jsonString);

                                                }

                                            }
                                            System.out.println(jsonString.toString().substring(0, jsonString.length() - 1));
                                            pmd.setJsonString("{" + jsonString.toString().substring(0, jsonString.length() - 1) + "\\n}");
                                        }
                                        System.out.println("\n}");

                                    }
                                }
                            }
                        }
                        if (pmd != null) {
                            pmdList.add(pmd);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pmdList;
    }


    private static void getJSONString(Field fld, StringBuffer jsonString) {
        String key = "\\n\\\"" + fld.getName() + "\\\"";
        String val = null;
        String sn = fld.getType().getSimpleName();
        if (sn.equalsIgnoreCase("String")) {
            val = "str";
            jsonString.append(key + ":\\\"" + val + "\\\",");
        } else if (sn.equalsIgnoreCase("Float")) {
            val = "10.0";
            jsonString.append(key + ":\\\"" + val + "\\\",");
        } else if (sn.equalsIgnoreCase("Double")) {
            val = "10.0";
            jsonString.append(key + ":\\\"" + val + "\\\",");
        } else if (sn.equalsIgnoreCase("Integer")) {
            val = "10";
            jsonString.append(key + ":\\\"" + val + "\\\",");
        } else if (sn.equalsIgnoreCase("Date") || sn.equalsIgnoreCase("TimeStamp")) {
            val = "2016-12-31";
            jsonString.append(key + ":\\\"" + val + "\\\",");
        } else if (sn.endsWith("Enum")) {
            val = sn;
            jsonString.append(key + ":\\\"" + val + "\\\",");
        } else if (key.contains("mail")) {
            val = "{@gmail.com}";
            jsonString.append(key + ":\\\"" + val + "\\\",");
        } else {
            val = "{" + fld.getName() + "Id}";
            jsonString.append(key + ":\\\"" + val + "\\\",");
        }
    }

    public static void getClassSwitch() {
        try {
            File folder = new File(folderName);
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                String fileName = listOfFiles[i].getName();
                fileName = fileName.substring(0, fileName.length() - 5);
                System.out.println("case \"" + fileName + "\":");
                System.out.println("    c=" + fileName + ".class;");
                System.out.println("break;");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
