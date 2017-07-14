public class Assignment2 {

    private final static String right="Ok";
    private final static String wrong="not Ok";
    private static BinaryTree binarytree;



    private static <T> String convertTestResultToString(T result, T expectedResult){
        if (result==null){
            if (expectedResult==null){
                return right;
            }
            else
                return wrong;
        }
        if (result.equals(expectedResult))
            return right;
        return wrong;
    }
    public static void createBinaryTree(){
        binarytree=new BinaryTree(5);
        binarytree.add(6,5,true);
        binarytree.add(7,5,false);
        binarytree.add(8,7,false);
        binarytree.add(0,8,true);
        binarytree.add(9,8,false);
        binarytree.add(10,7, true);
        binarytree.add(11,6,true);
        binarytree.add(12,6,true);
        binarytree.add(12,6,false);
        binarytree.add(13,12,false);
    }
    public static void testAllAnc(){
        System.out.println("part 1 of assignment2 test:");
        System.out.println(convertTestResultToString(binarytree.allAncestors(13),"12 6 5"));
        System.out.println(convertTestResultToString(binarytree.allAncestors(0),"8 7 5"));
        System.out.println(convertTestResultToString(binarytree.allAncestors(5),""));
        System.out.println(convertTestResultToString(binarytree.allAncestors(18),""));
        System.out.println(convertTestResultToString(binarytree.allAncestors(11),"6 5"));
        System.out.println(convertTestResultToString(binarytree.allAncestors(9),"8 7 5"));
        System.out.println(convertTestResultToString(binarytree.allAncestors(8),"7 5"));
    }

    public static void testCommonAnc(){
        System.out.println("part 2 of assignment2 test:");
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(13,11),"6"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(9,0),"8"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(9,11),"5"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(5,5),"5"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(11,11),"11"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(10,9),"7"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(7,0),"7"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(13,10),"5"));
        System.out.println(convertTestResultToString(binarytree.minCommonAncestors(130,10),""));
    }


    public static void main(String[] args) {
        createBinaryTree();
        testAllAnc();
        testCommonAnc();
    }

}


class BinaryTree <T>{
    class Node <T>{
        T value;
        Node left;
        Node right;
        Node<T> next;
        public Node(T value){
            this.value=value;
            left=null;
            right=null;
        }
        public boolean setLeft(Node node){
            if (left==null){
                left=node;
                return true;
            }
            return false;
        }
        public boolean setRight(Node node){
            if (right==null){
                right=node;
                return true;
            }
            return false;
        }
    }
    Node root;
    public BinaryTree(T value){
        root=new Node (value);
    }
    public Node find (T value){
        return find(root, value);
    }
    private Node find(Node node, T value){
        Node search=null;
        if (node.value==value)
            return node;
        if (node.left!=null)
            search=find(node.left, value);
        if (search==null && node.right!=null){
            search=find(node.right, value);
        }
        return search;
    }
    public boolean add(T value, T parentValue, boolean isLeft){
        Node nodeParent=find(parentValue);
        if (nodeParent==null)
            return false;
        Node node=new Node(value);
        if (isLeft){
            return nodeParent.setLeft(node);
        }
        if (!isLeft){
            return nodeParent.setRight(node);
        }
        return false;
    }
    public String allAncestors(T value){
        if (root.value.equals(value))
            return "";
        return allAncestors(root, value).trim();
    }
    private String allAncestors(Node node, T value){
        if (node==null)
            return "";
        if (node.value.equals(value))
            return "me";
        String left=allAncestors(node.left, value);
        String right=allAncestors(node.right, value);
        if (!left.equals("") || !right.equals("")){
            if (left.equals("me")|| right.equals("me")){
                right="";
                left="";
            }
            if (left.equals("")){
                return right+String.valueOf(node.value)+" ";
            }
            else{
                return left+String.valueOf(node.value)+" ";
            }
        }
        return "";
    }
    public String minCommonAncestors(T value1, T value2){
        String str= minCommonAncestors(root,value1,value2);
        if (str.equals(String.valueOf(value1)))
            if (find(value2)==null)
                return "";
        if (str.equals(String.valueOf(value2)))
            if (find(value1)==null)
                return "";
        return str;
    }
    private String minCommonAncestors(Node node, T value1, T value2){
        if (node==null)
            return "";
        if (node.value.equals(value1))
            return String.valueOf(value1);
        if (node.value.equals(value2))
            return String.valueOf(value2);
        String left=minCommonAncestors(node.left, value1, value2);
        String right=minCommonAncestors(node.right, value1, value2);
        if (!right.equals("") && !left.equals("")){
            return String.valueOf(node.value);
        }
        if (!right.equals(""))
            return right;
        return left;
    }
}