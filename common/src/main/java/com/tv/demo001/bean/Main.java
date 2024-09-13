package com.tv.demo001.bean;

/**
 * 引用作为参数传递给方法，则该引用在方法的局部变量生效.
 * 外部引用地址变更，不会更改该方法栈的内存中保存的的引用地址。
 *
 * @author hubo88
 * @description
 * @date 2024/5/29 11:03 AM
 */
public class Main {

    static ChildVertex childVertex = new ChildVertex(1, "billy");

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();

        Thread lookThread = new Thread(new Runnable(){
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("object: " + childVertex);
                }
            }
        });
        lookThread.setDaemon(false);
        lookThread.start();

        new Thread(new Runnable(){
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                childVertex = new ChildVertex(2, "allen");
                System.out.println("change object: " + childVertex);
            }
        }).start();

        main.solution(childVertex);

    }

    public void solution(ChildVertex childVertex) throws InterruptedException {
        System.out.println(childVertex);
        Thread.sleep(5000);
        System.out.println(childVertex);
        Thread.sleep(5000);
        childVertex = new ChildVertex(3, "john");
        System.out.println(childVertex.getName());
    }


}
