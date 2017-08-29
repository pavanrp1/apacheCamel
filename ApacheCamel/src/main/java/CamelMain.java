import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * 
 */

/**
 * @author PK015603
 *
 */
public class CamelMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 CamelContext context = new DefaultCamelContext();
		    context.addRoutes(new RouteBuilder()
		      {
		      public void configure()
		        {
		        // Set up the route � from the intput directory, to
		        //  the output directory, with no other processing
		    	System.out.println("Started moving");  
		        from ("file:C:/CTS_Infra/ApacheCamel/Input?noop=true").to("file:C:/CTS_Infra/ApacheCamel/Output");
		        }
		      });
		    //
		    // Start the Camel route
		    System.out.println("Start camel route");
		    context.start();

		    // Wait five minutes, then stop
		    Thread.sleep (2000);
		    System.out.println("Stop camel route");
		    context.stop ();

	}

}
