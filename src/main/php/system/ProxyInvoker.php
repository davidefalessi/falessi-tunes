<?php

    class ProxyInvoker
    {

        private $host;

        function __construct( $host )
        {
            $this->host = $host;
        }

        public function invoke( $method, $path )
        {
            $url = $this->host . $path;
            $ch = curl_init( $url );
            curl_setopt( $ch, CURLOPT_CUSTOMREQUEST, $method );
            curl_setopt( $ch, CURLOPT_HEADER, false );
            curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );

            if ( 'GET' == $method )
            {
                curl_setopt( $ch, CURLOPT_HTTPHEADER, array( 'Accept: application/json' ) );
            }
            else if ( 'POST' == $method )
            {
                curl_setopt( $ch, CURLOPT_POST, true );
                curl_setopt( $ch, CURLOPT_POSTFIELDS, http_build_query( $_POST ) );
            }
            else if ( 'PUT' == $method )
            {
                curl_setopt( $ch, CURLOPT_HTTPHEADER, array( 'Content-type: application/json' ) );
                $cont = file_get_contents( 'php://input' );
                curl_setopt( $ch, CURLOPT_POSTFIELDS, $cont );
            }

            $response = curl_exec( $ch );
            $http_status = curl_getinfo( $ch, CURLINFO_HTTP_CODE );

            return new ProxyInvocation( $http_status, $response );
        }

    }

    class ProxyInvocation
    {

        private $http_status;

        private $body;

        function __construct( $http_status, $body )
        {
            $this->http_status = $http_status;
            $this->body = $body;
        }

        public function getHttpStatus()
        {
            return $this->http_status;
        }

        public function getBody()
        {
            return $this->body;
        }

    }

?>
