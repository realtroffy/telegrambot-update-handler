package telegrambot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "question")
public class CHGKQuestion implements Serializable {

    private static final long serialVersionUID = 1683874324808432289L;

    @JacksonXmlProperty(localName = "Question")
    private String question;
    @JacksonXmlProperty(localName = "Answer")
    private String answer;
    @JacksonXmlProperty(localName = "Comments")
    private String comment;
}
