package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class GraphNode {
    private String name;
    private Map<String,Object> nodeAttributes ;

    public GraphNode(String name) {

        this(name,new HashMap<>());
    }

    public GraphNode(String name, Map<String, Object> nodeAttributes) {
       if(name==null || name.isBlank()){
           throw new IllegalArgumentException("name cant be null");
       }
        this.name = name;
        this.nodeAttributes = new HashMap<>(nodeAttributes);
    }

    public String getName() {
        return name;
    }
    public Object getNodeAttribute(String attributeName){
        return nodeAttributes.get(attributeName);
    }
    public void setNodeAttribute(String attributeName,Object value){
          nodeAttributes.put(attributeName,value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNode graphNode = (GraphNode) o;

        return name.equals(graphNode.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GraphNode{");
        sb.append("name='").append(name).append('\'');
        sb.append(", nodeAttributes=").append(nodeAttributes);
        sb.append('}');
        return sb.toString();
    }
}
