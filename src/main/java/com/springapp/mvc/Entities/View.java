package com.springapp.mvc.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "view")
public class View extends BaseModel {

    @Column(name = "TYPE")
    @NotEmpty
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "view", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CharacteristicAttribute> attributes;

    public View() {
        super();
    }


    public View(int id,String type,String description) {
        this.setId(id);
        this.type=type;
        this.description=description;

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<CharacteristicAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CharacteristicAttribute> attributes) {
        this.attributes = attributes;
    }
}
