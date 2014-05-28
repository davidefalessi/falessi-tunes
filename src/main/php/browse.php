<?php
    require_once 'system/Templater.php';

    $form = new Templater();
    $form->load( "templates/browse.tpl.php" );
    $page = new Templater( "templates/main.tpl.php" );

    $page->title = "List/Remove Authors";
    $page->scripts = array( "browse.js" );
    $page->set( "body", $form->parse() );

    $page->publish();
?>
