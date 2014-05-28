/*
 *    Copyright 2014 Davide Falessi
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package it.itis.pertini.falessi.tunes;

import io.airlift.command.Command;
import io.airlift.command.Option;

import java.util.Date;
import java.util.Formatter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

import com.google.inject.servlet.GuiceFilter;

@Command( name = "run", description = "Runs the Tunes server" )
public final class TunesRunner
    implements Runnable
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @Option( name = { "-X", "--verbose" }, description = "Produce execution debug output." )
    private boolean debug;

    @Option( name = { "-p", "--port" }, description = "The HTTP Server port (8080 by default)." )
    private Integer port = 8080;

    public void run()
    {
        if ( debug )
        {
            System.setProperty( "logging.level", "DEBUG" );
        }
        else
        {
            System.setProperty( "logging.level", "INFO" );
        }

        // assume SLF4J is bound to logback in the current environment
        final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        try
        {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext( lc );
            // the context was probably already configured by default configuration
            // rules
            lc.reset();
            configurator.doConfigure( getClass().getClassLoader().getResourceAsStream( "logback-config.xml" ) );
        }
        catch ( JoranException je )
        {
            // StatusPrinter should handle this
        }

        logger.info( "" );
        logger.info( "                         ''~``" );
        logger.info( "                        ( o o )" );
        logger.info( "+------------------.oooO--(_)--Oooo.------------------+" );
        logger.info( "" );
        logger.info( "Starting Falessi Tunes 1.0.0" );
        logger.info( "" );
        logger.info( "                     .oooO                            " );
        logger.info( "                     (   )   Oooo.                    " );
        logger.info( "+---------------------\\ (----(   )--------------------+" );
        logger.info( "                       \\_)    ) /" );
        logger.info( "                             (_/" );

        // Create the server.
        final Server server = new Server( port );

        // Create a servlet context and add the jersey servlet.
        final ServletContextHandler sch = new ServletContextHandler( server, "/" );

        // Add our Guice listener that includes our bindings
        sch.addEventListener( new TunesContextListener() );

        // Must add DefaultServlet for embedded Jetty.
        // Failing to do this will cause 404 errors.
        // This is not needed if web.xml is used instead.
        sch.addServlet( DefaultServlet.class, "/" );

        // Then add GuiceFilter and configure the server to
        // reroute all requests through this filter.
        sch.addFilter( GuiceFilter.class, "/*", null );

        // Add the Shutdown Hook  to the Java virtual machine
        // in order to destroy all the allocated resources
        final long start = System.currentTimeMillis();

        Runtime.getRuntime().addShutdownHook( new Thread( "shutdown-hook" )
        {

            public void run()
            {
                logger.info( "" );
                logger.info( "                         ''~``" );
                logger.info( "                        ( o o )" );
                logger.info( "+------------------.oooO--(_)--Oooo.------------------+" );

                try
                {
                    server.stop();
                    server.destroy();
                }
                catch ( Exception e )
                {
                    logger.warn( "An error occurred while stopping the server, see nested exceptions", e );
                }

                // format the uptime string

                Formatter uptime = new Formatter();
                uptime.format( "Total uptime:" );

                long uptimeInSeconds = ( System.currentTimeMillis() - start ) / 1000;
                final long hours = uptimeInSeconds / 3600;

                if ( hours > 0 )
                {
                    uptime.format( " %s hour%s", hours, ( hours > 1 ? "s" : "" ) );
                }

                uptimeInSeconds = uptimeInSeconds - ( hours * 3600 );
                final long minutes = uptimeInSeconds / 60;

                if ( minutes > 0 )
                {
                    uptime.format( " %s minute%s", minutes, ( minutes > 1 ? "s" : "" ) );
                }

                uptimeInSeconds = uptimeInSeconds - ( minutes * 60 );

                if ( uptimeInSeconds > 0 )
                {
                    uptime.format( " %s second%s", uptimeInSeconds, ( uptimeInSeconds > 1 ? "s" : "" ) );
                }

                logger.info( uptime.toString() );
                logger.info( "Finished at: {}", new Date() );

                final Runtime runtime = Runtime.getRuntime();
                final int megaUnit = 1024 * 1024;
                logger.info( "Final Memory: {}M/{}M",
                             ( runtime.totalMemory() - runtime.freeMemory() ) / megaUnit,
                             runtime.totalMemory() / megaUnit );

                logger.info( "                     .oooO                            " );
                logger.info( "                     (   )   Oooo.                    " );
                logger.info( "+---------------------\\ (----(   )--------------------+" );
                logger.info( "                       \\_)    ) /" );
                logger.info( "                             (_/" );

                uptime.close();
            }

        } );

        // Finally start the server!!!

        try
        {
            server.start();
            server.join();
        }
        catch ( Exception e )
        {
            logger.error( "The HTTP Server cannot be started, see nested exceptions", e );
            System.exit( 1 );
        }
    }

}
