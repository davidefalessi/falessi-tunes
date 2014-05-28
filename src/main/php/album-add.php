<?php

    require_once 'system/Templater.php';

    $form = new Templater();
    $form->load( "templates/album-add.tpl.php" );
    $page = new Templater( "templates/main.tpl.php" );

    $page->title = "Add a new Album";
    $page->styles = array( "datepicker.css", "datepicker3.css" );
    $page->scripts = array( "bootstrap-datepicker.js", "album-add.js" );
    $page->set( "body", $form->parse() );

    $page->publish();
?>
