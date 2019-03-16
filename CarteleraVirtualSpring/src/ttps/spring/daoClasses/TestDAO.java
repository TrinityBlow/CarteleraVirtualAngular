package ttps.spring.daoClasses;

import ttps.spring.modelCartelera.Test;

public interface TestDAO extends GenericDAO<Test> {

	public Test recuperarTest(String nom);
	public boolean existeTest(String nom);
	public Test borrarLogico(String nom);

}
