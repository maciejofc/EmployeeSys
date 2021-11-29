package pl.maciejowsky.employeemanagement.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Title")
@Table(
        name = "titles",
        uniqueConstraints = {
                @UniqueConstraint(name = "employee_title_unique", columnNames = "title_name")
        }
)
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "title_id"
    )
    private Long id;

    @Column(
            name = "title_name",
            nullable = false
    )
    private String title;

    public Title() {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }



    public Title(String title) {
        this.title = title;
    }
}
