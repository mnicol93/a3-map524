package com.example.a3_marcn_mnicolas_oliva.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
        "data": [
          {
            "attack": 0,
            "category": "equipment",
            "common_locations": [
                "Tabantha Frontier",
                "Gerudo Desert"
            ]
            "defense": 22,
            "description": "This Lizal shield has been strengthened by
                adding a different type of metal to the mix. The edge is line
                with spikes, so handle with care."
            "id": 378,
            "image": "https://botw-compendium.herokuapp.com/api/v2/entry/reinforced_lizal_shield/image",
            "name": "reinforced lizal shield"
          },
          ....
        ]
    }
* */
public class Zelda {
    private String name;
    private String description;
    private List<String> common_locations;
    private int attack;
    private int defense;

    @SerializedName("image")
    private String imageURL;

    // getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCommon_locations() {
        return common_locations;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getImageURL() {
        return imageURL;
    }

    // toString

    @Override
    public String toString() {
        return "Zelda{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", common_locations=" + common_locations +
                ", attack=" + attack +
                ", defense=" + defense +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
