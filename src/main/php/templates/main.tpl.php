<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Falessi Tunes | <?php print @$this->title; ?></title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/falessi-tunes.css">
    <?php
        if ( isSet( $this->styles ) )
        {
            foreach ( $this->styles as $style )
            {
    ?>
    <link rel="stylesheet" href="css/<?php print @$style; ?>">
    <?php
            }
        }
    ?>
  </head>
  <body role="document">

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.php">Falessi Tunes</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li><a href="author-add.php">Add Author</a></li>
            <li><a href="album-add.php">Add Album</a></li>
            <li><a href="track-add.php">Add Track</a></li>
            <li><a href="browse.php">Browse</a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="container theme-showcase" role="main">

      <?php print $this->body; ?>

      <div class="footer">
        <p>Copyright 2014 Davide Falessi</p>
      </div>

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- Auxiliary core JavaScript -->
    <?php
        if ( isSet( $this->scripts ) )
        {
            foreach ( $this->scripts as $script )
            {
    ?>
    <script src="js/<?php print @$script; ?>"></script>
    <?php
            }
        }
    ?>
  </body>
</html>
