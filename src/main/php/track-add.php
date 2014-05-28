<?php

    require_once 'system/Templater.php';

    $form = new Templater();
    $form->load( "templates/track-add.tpl.php" );
    $page = new Templater( "templates/main.tpl.php" );

    $page->title = "Add a new Track";
    $page->scripts = array( "bootstrap-datepicker.js", "track-add.js" );
    $page->set( "body", $form->parse() );

    $page->publish();
?>
