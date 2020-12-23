package libs;

import org.aeonbits.owner.Config;

public interface ConfigProperties extends Config {
	long TIME_FOR_DFFAULT_WAIT();
	long TIME_FOR_EXPLICIT_WAIT_LOW();
	long TIME_FOR_EXPLICIT_WAIT_HIGHT();

	String OPERATOR_LOGIN_FOR_TEST();
	String OPERATOR_PASSWORD_FOR_TEST();

	String base_url();
	String DATA_FILE();
	String DATA_FILE_PATH();
	String Oracle();
	String MySQL();
	String sqlServer();

	String MySQL_DB();
	String MySQL_DB_USER();
	String MySQL_DB_PASSWORD();

	String ORACLE_SQL_DB();
	String ORACLE_SQL_DB_USER();
	String ORACLE_SQL_DB_PASSWORD();

	String GET_SMS_CODE_FOR_AUTH();
	String GET_SMS_CODE_FOR_SELL();
}
