package com.example.projetdevmobile;

public class Character {
    private String name;
    private Integer id;
    private String role;
    private String origine;
    private String image;

    public String getName(){
        return name;
    }
    public Integer getId(){
        return id;
    }
    public String getImage() {
        return image;
    }

    public String getOrigine() {
        return origine;
    }

    public String getRole() {
        return role;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
