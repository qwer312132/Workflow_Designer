package src.canvas.object;

import java.util.ArrayList;

public class CompositeObject {
    private CompositeObject parent;
    private final ArrayList<CompositeObject> children = new ArrayList<>();
    private BasicObject owner;
    public void setOwner(BasicObject obj) {
        this.owner = obj;
    }

    public BasicObject getOwner() {
        return owner;
    }
    public void addChild(CompositeObject child) {
        children.add(child);
        child.setParent(this);
    }

    public void setParent(CompositeObject parent) {
        this.parent = parent;
    }

    public CompositeObject getParent() {
        return parent;
    }

    public ArrayList<CompositeObject> getChildren() {
        return children;
    }

    public CompositeObject getRoot() {
        CompositeObject node = this;
        while (node.parent != null) {
            node = node.parent;
        }
        return node;
    }

    public ArrayList<CompositeObject> getAllMembers(){
        CompositeObject root = getRoot();
        ArrayList<CompositeObject> members = new ArrayList<>();
        dfs(members, root);
        return members;
    }

    public void dfs(ArrayList<CompositeObject> compositeObjects, CompositeObject node){
        if(node.getOwner() != null){
            compositeObjects.add(node);
        }
        for (CompositeObject child : node.getChildren()) {
            dfs(compositeObjects, child);
        }
    }

    public void move(int x, int y){
        ArrayList<CompositeObject> compositeObjects = getAllMembers();
        for(CompositeObject compositeObject:compositeObjects){
            compositeObject.getOwner().move(x, y);
        }
    }

    public void select(){
        ArrayList<CompositeObject> compositeObjects = getAllMembers();
        for(CompositeObject compositeObject:compositeObjects){
            compositeObject.getOwner().setSelected(true);
        }
    }

    public void ungroup(){
        for(CompositeObject compositeObject:this.children){
            compositeObject.setParent(null);
        }
        this.children.clear();
    }
}
