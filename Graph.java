import java.util.ArrayList;

/**
 * Created by marya on 14.7.17.
 */
public class Graph {
    private static class Node {
        private char letter;
        private ArrayList <Node> neiborhoods;
        private int numberOfCurrentNode;
        private int state;

        public Node (char letter) {
            this.letter = letter;
            neiborhoods = new ArrayList<>();
            numberOfCurrentNode = -1;
            state = 0;
        }
        public boolean addNeiborhood (Node neiborhood){
            if (neiborhoods.isEmpty()){
                neiborhoods.add(neiborhood);
                return true;
            }
            if (neiborhoods.contains(neiborhood))
                return false;
            neiborhoods.add(neiborhood);
            return true;
        }
        public Node getNext(){
            state = 1;
            numberOfCurrentNode++;
            if (numberOfCurrentNode>=neiborhoods.size()){
                state = 2;
                return null;
            }
            return neiborhoods.get(numberOfCurrentNode);
        }
        public int getState(){
            return state;
        }
        public char getLetter() {return letter;}
        @Override
        public boolean equals(Object obj){
            if (obj == null) {
                return false;
            }
            if (!Node.class.isAssignableFrom(obj.getClass())) {
                return false;
            }
            final Node other = (Node) obj;
            if (letter != other.letter)
                return false;
            return true;
        }
        @Override
        public String toString(){
            return letter+" ";
        }
    }

    private ArrayList <Node> nodes;

    public Graph(){
        nodes=new ArrayList<>();
    }
    public boolean addLetter(char letter){
        Node node = new Node (letter);
        int index = nodes.indexOf(node);
        if (index >= 0)
            return false;
        nodes.add(node);
        return true;
    }
    public boolean addRelations (char letterBigger, char letterSmaller){
        Node nodeBig = new Node (letterBigger);
        Node nodeLittle = new Node (letterSmaller);
        int index = nodes.indexOf (nodeBig);
        if (index < 0){
            nodes.add(nodeBig);
        }
        else{
            nodeBig = nodes.get(index);
        }
        index = nodes.indexOf (nodeLittle);
        if (index < 0){
            nodes.add(nodeLittle);
        }
        else{
            nodeLittle = nodes.get(index);
        }
        return nodeBig.addNeiborhood (nodeLittle);
    }

    public ArrayList <Character> topologicalSortResult(){
        ArrayList <Character> answer = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++){
            if (nodes.get(i).getState() == 0){
                if (dfs (nodes.get(i), answer) == false)
                    return null;
            }
        }
        return answer;
    }
    private boolean dfs (Node node, ArrayList <Character> answer){
        if (node == null)
            return true;
        Node next = node.getNext();
        boolean flag = true;
        while (next != null){
            if (next.getState() == 1)
                return false;
           if(next.getState() != 2){
               flag = flag & dfs(next, answer);
           }
           next = node.getNext();
        }
        answer.add (node.getLetter());
        return flag;
    }
    @Override
    public String toString(){
        return nodes.toString();
    }
}