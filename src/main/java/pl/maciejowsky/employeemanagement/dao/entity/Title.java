package pl.maciejowsky.employeemanagement.dao.entity;

import javax.persistence.*;

@Entity(name = "Title")
@Table(
        name = "titles")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "title_no"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String title;

    public Title() {
    }

    public Title(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }


}
