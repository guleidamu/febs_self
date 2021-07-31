package com.damu.febs.server.test.byMySelf.generics;

public class GlmapperGeneric<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GlmapperGeneric<Object> objectGlmapperGeneric = new GlmapperGeneric<>();
        objectGlmapperGeneric.noSpecifyType();
        objectGlmapperGeneric.specifyType();

    }


    /**
     * 不指定类型
     */

    public void noSpecifyType(){
        GlmapperGeneric glmapperGeneric = new GlmapperGeneric<>();
        glmapperGeneric.setT("test");
        String test = (String)glmapperGeneric.getT();
        System.out.println("noSpecifyType" + test);
    }

    public void specifyType(){
        GlmapperGeneric<String> stringGlmapperGeneric = new GlmapperGeneric<>();
        stringGlmapperGeneric.setT("test");
        String t = stringGlmapperGeneric.getT();
        System.out.println( "specifyType" + t);
    }
}
