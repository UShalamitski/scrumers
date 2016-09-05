package com.scrumers.model;

public class ProductView extends Product {

    private static final long serialVersionUID = 1L;

    private String ownerName;

    public ProductView(){}
    
    public ProductView(Product p){
        setName(p.getName());
        setDescr(p.getDescr());
        setOwner(p.getOwner());
        setDone(p.isDone());
        setId(p.getId());
        setIdCreator(p.getIdCreator());
        setAdded(p.getAdded());
        setModified(p.getModified());
        setDel(p.getDel());
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}
