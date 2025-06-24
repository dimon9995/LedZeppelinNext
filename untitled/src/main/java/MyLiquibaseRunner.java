import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;

public class MyLiquibaseRunner {


    public  static void main(String[] args) throws Exception {
        System.out.println("Running Liquibase...");

        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
            CommandScope update = new CommandScope("update");

            update.addArgumentValue("changelogFile", "chage.xml");
            update.addArgumentValue("url", "jdbc:postgresql://localhost:2345/demo");
            update.addArgumentValue("username", "postgres");
            update.addArgumentValue("password", "postgres");

            update.execute();
        });

        System.out.println("Running Liquibase...DONE");
    }
}