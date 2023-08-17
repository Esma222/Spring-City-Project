package com.esmabeydili.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data//getter//setter
@NoArgsConstructor//parametresiz constructor//çalışmadı
@AllArgsConstructor//tüm parametreleri alan constructor//çalışmadı
public class Il {

    private Date createDate= new Date();

    @Id
    private String id;

    private String name;

   /* public Il(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Il() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
