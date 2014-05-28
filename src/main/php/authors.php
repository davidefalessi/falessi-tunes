<?php
    require_once 'system/ProxyInvoker.php';
    require_once 'system/proxy-config.php';
    require_once 'system/Templater.php';
    require_once 'system/commons-proxied.php';

    $path = '/authors';

    $proxyInvoker = new ProxyInvoker( $host );
    $response = $proxyInvoker->invoke( $method, $path );
    $responseBody = $response->getBody();
    $authors = json_decode( $responseBody );

    $page = new Templater( "templates/authors.tpl.php" );

    foreach ( $authors as $author )
    {
        foreach ( $author as $key => $value )
        {
            $page->set( $key, $value );
        }

        $page->publish();
    }
?>