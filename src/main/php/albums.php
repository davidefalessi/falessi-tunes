<?php
    require_once 'system/ProxyInvoker.php';
    require_once 'system/proxy-config.php';
    require_once 'system/Templater.php';
    require_once 'system/commons-proxied.php';

    $pathinfo = explode( '/', trim( $_SERVER['PATH_INFO'], '/' ) );
    $path = '/authors' . $_SERVER['PATH_INFO'] . '/albums';

    $proxyInvoker = new ProxyInvoker( $host );
    $response = $proxyInvoker->invoke( $method, $path );
    $responseBody = $response->getBody();
    $albums = json_decode( $responseBody );

    $page = new Templater( "templates/albums.tpl.php" );

    $counter = 1;
    $size = sizeof( $albums );
    $page->set( 'size', $size );
    $page->set( 'authorId', $pathinfo[0] );

    foreach ( $albums as $album )
    {
        $page->set( 'counter', $counter++ );

        foreach ( $album as $key => $value )
        {
            $page->set( $key, $value );
        }

        $page->publish();
    }
?>