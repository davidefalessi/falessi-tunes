<?php
    require_once 'ProxyInvoker.php';
    require_once 'proxy-config.php';

    header( 'Cache-Control: no-cache, must-revalidate' );
    header( 'Expires: Mon, 26 Jul 1997 05:00:00 GMT' );

    $method = $_SERVER['REQUEST_METHOD'];
    $path = $_SERVER['PATH_INFO'];

    $proxyInvoker = new ProxyInvoker( $host );

    if ( 'GET' == $method )
    {
        header( 'Content-type: application/json' );
    }
    else if ( 'POST' == $method )
    {
        header( 'Content-type: text/plain' );
    }
    else if ( 'PUT' == $method )
    {
        header( 'Content-type: text/plain' );
    }
    else if ( 'DELETE' == $method )
    {
        header( 'Content-type: text/plain' );
    }

    $response = $proxyInvoker->invoke( $method, $path );
    http_response_code( $response->getHttpStatus() );

    echo $response->getBody();
?>
