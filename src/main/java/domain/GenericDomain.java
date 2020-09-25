package domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {

	@Id
	private Integer ident;
	
	public Integer getIdent() {
		return ident;
	}
}
