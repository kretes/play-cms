package models.cms;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

@Entity
public class CMSPage extends GenericModel {

	private static final int PREVIEW_SIZE = 100;

	@Required
	@Id
	public String name;
	@Required
	public String title;
	@Required
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(length = 10000)
	public String body;
	public boolean active;
	public Integer sort;
	public String tags;

	public static List<CMSPage> findByTag(String tag) {
		return CMSPage.find("tags like ? order by sort", "%" + tag + "%")
				.fetch();
	}

	public String getBodyPreview() {
		return body.substring(0,(body.length() < PREVIEW_SIZE) ? body.length() : PREVIEW_SIZE) ;
	}
}
