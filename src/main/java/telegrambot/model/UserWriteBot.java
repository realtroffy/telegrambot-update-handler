package telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.ZoneId.of;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserWriteBot implements Serializable {

  private static final long serialVersionUID = 1450259395197917962L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_write_generator")
  @SequenceGenerator(
      name = "user_write_generator",
      sequenceName = "user_write_sequence",
      allocationSize = 3)
  @Column
  private Long id;

  @Column private String firstName;

  @Column private String lastName;

  @CreatedDate @Column private LocalDateTime dateMessage;

  @Column private String buttonName;

  @PrePersist
  public void onCreate() {
    this.dateMessage = LocalDateTime.now(of("Europe/Minsk"));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    UserWriteBot that = (UserWriteBot) o;

    return new EqualsBuilder()
        .append(id, that.id)
        .append(firstName, that.firstName)
        .append(lastName, that.lastName)
        .append(dateMessage, that.dateMessage)
        .append(buttonName, that.buttonName)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(firstName)
        .append(lastName)
        .append(dateMessage)
        .append(buttonName)
        .toHashCode();
  }
}
