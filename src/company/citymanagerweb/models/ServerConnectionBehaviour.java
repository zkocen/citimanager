package company.citymanagerweb.models;

import java.sql.Connection;

public interface ServerConnectionBehaviour 
{
	Connection getConnection();
	String getConnectionURL();
	String getConnectionDetails();
	String getTablesSchemaQuery();
}