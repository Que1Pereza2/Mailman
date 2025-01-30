package mdlds.inc.mailman.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Baljeet
 */

//  We define the model as an entity so Spring Boot treats it as one.
@Entity
// Here we inform Spring Boot of the table's name where this entity is located.
@Table(name = "city")
public class City {
   
//  We define the id attribute which will be automatically generated, not null, and used for the identity of every element.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;
    
//  We define the name of the city.
    @Column(nullable = false)
    private String name;
     
// We define the name of the state.
    @Column()
    private String state;
    
// We define the area of the city.
    @Column()
    private int km2;
    
// We define the one to many relationship with itself and name the variable "idStart".
    @OneToMany( mappedBy = "idStart", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private List<Link> linksStart;
    
// We define the one to many relationship with itself and name the variable "idEnd".
    @OneToMany( mappedBy = "idEnd", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private List<Link> linksEnd;
    
// Constructor for the class.
    public City(){
        
    }

//    Getters and setters.
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getKm2() {
        return km2;
    }

    public void setKm2(int km2) {
        this.km2 = km2;
    }

    public List<Link> getLinksStart() {
        return linksStart;
    }

    public void setLinksStart(List<Link> linksStart) {
        this.linksStart = linksStart;
    }

    public List<Link> getLinksEnd() {
        return linksEnd;
    }

    public void setLinksEnd(List<Link> linksEnd) {
        this.linksEnd = linksEnd;
    }
    
}
