<?php
    require_once 'system/ProxyInvoker.php';
    require_once 'system/Templater.php';
    require_once 'system/proxy-config.php';
    require_once 'system/commons-proxied.php';

    $pathinfo = explode( '/', trim( $_SERVER['PATH_INFO'], '/' ) );
    $path = '/authors/' . $pathinfo[0] . '/albums/' . $pathinfo[1] . '/tracks';

    $proxyInvoker = new ProxyInvoker( $host );
    $response = $proxyInvoker->invoke( $method, $path );
    $responseBody = $response->getBody();
    $tracks = json_decode( $responseBody );

    $page = new Templater( "templates/tracks.tpl.php" );

    $counter = 0;
    $size = sizeof( $tracks );
    $page->set( 'size', $size );

    foreach ( $tracks as $track )
    {
        $page->set( 'counter', $counter++ );

        foreach ( $track as $key => $value )
        {
            $page->set( $key, $value );
        }

        $page->publish();
    }
?>