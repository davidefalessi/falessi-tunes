<?php
    require_once "system/Templater.php";

    $form = new Templater();
    $form->load( "templates/index.tpl.php" );
    $page = new Templater( "templates/main.tpl.php" );

    $page->title = "Homepage";
    $page->set( "body", $form->parse() );

    $page->publish();
?>
