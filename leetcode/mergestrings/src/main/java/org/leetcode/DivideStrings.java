package org.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DivideStrings {
    public static String gcdOfStringsV0(String str1, String str2) {
        if (str1.equals(str2)) {
            return str1;
        } else {
            List<String> str1DividerList = getDividerV0(str1);
            //System.out.println("Divider 1: " + str1Divider);
            List<String> str2DividerList = getDividerV0(str2);
            //System.out.println("Divider 2: " + str2Divider);

            for (String div1: str1DividerList) {
                //System.out.println(div1);
                if (str2DividerList.contains(div1)) {
                    return div1;
                }
            }
            return "";

//            if (str1Divider.equals(str2Divider) || str1Divider.equals(str2)) {
//                return str1Divider;
//            } else if (str2Divider.equals(str1)) {
//                return str2Divider;
//            } else {
//                return "";
//            }
        }
    }

    static List<String> getDividerV0(String str) {
        LinkedList<String> dividerList = new LinkedList<>();
        dividerList.add(str);
        String divider = "";
        for (int dividerLength = str.length()-1; dividerLength > 0; dividerLength--) {
            boolean hasDivider = false;
            for (int j = dividerLength; j <= str.length()/2; j = j + dividerLength) {
                divider = str.substring(0, dividerLength);
                //System.out.println(divider);
                //System.out.println(dividerLength);
                String cmp = str.substring(j, j + dividerLength);
                //System.out.println(cmp);
                if (cmp.equals(divider) && str.length()% dividerLength == 0) {
                    //divider = str;
                    hasDivider = true;
                } else {
                    hasDivider = false;
                    break;
                }
            }
            if (hasDivider) {
                dividerList.add(divider);
            }
        }
        return dividerList;
    }


    public static String gcdOfStringsV1(String str1, String str2) {
        if (str1.equals(str2)) {
            return str1;
        } else {
            if (str1.length() <= str2.length()) {
                List<String> str1DividerList = getDividerV1(str1);

                for (String div1 : str1DividerList) {
                    if (div1.equals(getDividerV1(str2, div1.length()))) {
                        return div1;
                    }
                }
                return "";
            } else {
                List<String> str2DividerList = getDividerV1(str2);

                for (String div2 : str2DividerList) {
                    if (div2.equals(getDividerV1(str1, div2.length()))) {
                        return div2;
                    }
                }
                return "";
            }
        }
    }

    static List<String> getDividerV1(String str) {
        LinkedList<String> dividerList = new LinkedList<>();
        dividerList.add(str);
        for (int dividerLength = str.length()-1; dividerLength > 0; dividerLength--) {
            String div = getDividerV1(str, dividerLength);
            if (!"".equals(div)) {
                dividerList.add(div);
            }

        }
        return dividerList;
    }

    static String getDividerV1(String str, int dividerLength) {
        boolean hasDivider = false;
        String divider = "";
        for (int j = dividerLength; j <= str.length()/2; j = j + dividerLength) {
            divider = str.substring(0, dividerLength);
            String cmp = str.substring(j, j + dividerLength);
            if (cmp.equals(divider) && str.length()% dividerLength == 0) {
                hasDivider = true;
            } else {
                hasDivider = false;
                break;
            }
        }
        if (hasDivider) {
            return divider;
        } else {
            return "";
        }
    }


    public static String gcdOfStrings(String str1, String str2) {
        if (str1.equals(str2)) {
            return str1;
        } else {
            if (str1.length() <= str2.length()) {
                if (str1.equals((getDivider(str2, str1.length())))) {
                    return str1;
                }

                int dividerLength = str1.length()-1;
                while (dividerLength>0) {
                    if (str1.length()%dividerLength == 0) {
                        String divider = getDivider(str1, dividerLength);
                        if (!"".equals(divider)) {
                            if (divider.equals(getDivider(str2, divider.length()))) {
                                return divider;
                            }
                        }
                    }
                    dividerLength = dividerLength - 1;
                }
                return "";
            } else {
                return gcdOfStrings(str2, str1);
            }
        }
    }

    private static String getDivider(String str, int dividerLength) {
        boolean hasDivider = false;
        String divider = "";
        for (int j = dividerLength; j <= str.length()/2; j = j + dividerLength) {
            divider = str.substring(0, dividerLength);
            String cmp = str.substring(j, j + dividerLength);
            if (cmp.equals(divider) && str.length()% dividerLength == 0) {
                hasDivider = true;
            } else {
                hasDivider = false;
                break;
            }
        }
        if (hasDivider) {
            return divider;
        } else {
            return "";
        }
    }


    public static String gcdOfStringsV3(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1))
            return "";

        int a = str1.length();
        int b = str2.length();
        int gcd;
        if(a > b)
            gcd = GCD(a,b);
        else
            gcd = GCD(b,a);
        return str1.substring(0,gcd);
    }

    public static int GCD(int a, int b){
        if(b==0)
            return a;
        return GCD(b,a % b);
    }


    public String gcdOfStringsV4(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1)) return "";
        return str1.substring(0, gcdV4(str1.length(), str2.length()));
    }
    public int gcdV4(int n1, int n2)
    {
        if(n2==0)
            return n1;
        return gcdV4(n2, n1%n2);
    }


    public String gcdOfStringsV5(String str1, String str2) {
        if(str1.length() < str2.length()){
            return gcdOfStringsV5(str2,str1);
        }else if(!str1.startsWith(str2)){
            return "";
        }else if(str2.isEmpty()){
            return str1;
        }else{
            return gcdOfStringsV5(str1.substring(str2.length()),str2);
        }
    }

}
