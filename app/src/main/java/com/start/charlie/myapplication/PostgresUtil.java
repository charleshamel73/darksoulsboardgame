package com.start.charlie.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by charlie on 5/24/17.
 */

public class PostgresUtil {
    private Connection c;

    public void connectToServer(String connectionUrl) throws SQLException, ClassNotFoundException {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "password");
            Statement state = c.createStatement();
        try {
            state.execute("CREATE TABLE public.class\n" +
                    "(\n" +
                    "    name character(100) NOT NULL,\n" +
                    "    heroic character(100) NOT NULL,\n" +
                    "    taunt integer NOT NULL,\n" +
                    "    CONSTRAINT class_name_key UNIQUE (name)\n" +
                    ")\n" +
                    "WITH (\n" +
                    "    OIDS = FALSE\n" +
                    ")\n" +
                    "TABLESPACE pg_default;\n" +
                    "\n" +
                    "ALTER TABLE public.class\n" +
                    "    OWNER to postgres;");
        } catch (Exception ex) {
            System.out.println("DUH");
        }
        try {
            state.execute("CREATE TABLE public.\"character\"\n" +
                    "(\n" +
                    "    id bigint NOT NULL,\n" +
                    "    name character(100) NOT NULL,\n" +
                    "    class character(100) NOT NULL,\n" +
                    "    CONSTRAINT character_pkey PRIMARY KEY (id),\n" +
                    "    CONSTRAINT character_class_fkey FOREIGN KEY (class)\n" +
                    "        REFERENCES public.class (name) MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION\n" +
                    "        ON DELETE NO ACTION\n" +
                    ")\n" +
                    "WITH (\n" +
                    "    OIDS = FALSE\n" +
                    ")\n" +
                    "TABLESPACE pg_default;\n" +
                    "\n" +
                    "ALTER TABLE public.\"character\"\n" +
                    "    OWNER to postgres;");
        } catch (Exception ex) {
            System.out.println("DUH");
        }
    }

    public void getListOfCharacters(){
        ArrayList<String> nameList = null;
        ResultSet rset = null;
        try {
            Statement statement = c.createStatement();
            rset = statement.executeQuery("SELECT name FROM character");
            while(rset.next()){
                System.out.println(rset.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClass(String name, String clazz, Integer taunt) {
        try {
            Statement statement = c.createStatement();
            statement.execute("INSERT INTO public.class(\n" +
                    "                name, heroic, taunt)\n" +
                    "        VALUES ('"+name+"', '"+clazz+"',"+taunt+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addCharacter(String name, String clazz, Integer id) {
        try {
            Statement statement = c.createStatement();
            statement.execute("INSERT INTO public.\"character\"(\n" +
                    "\tid, name, class)\n" +
                    "\tVALUES ("+id+", '"+name+"', '"+clazz+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
