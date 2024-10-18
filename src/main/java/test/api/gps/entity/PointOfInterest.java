package test.api.gps.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_points_of_interest")
public class PointOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long x;

    private Long y;

    private Long cep;

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public PointOfInterest() {

    }

    public PointOfInterest(String name, Long x, Long y, Long cep) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
