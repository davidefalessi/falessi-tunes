<?php
    if ( $this->counter == 1 || $this->counter % 3 == 0 )
    {
?>
<div class="row">
<?php
    }
?>

  <div class="col-md-4">
    <h2><?php print @$this->name; ?></h2>
    <p>Released on <i><?php print @$this->releaseDate; ?></i></p>
    <p><i><?php print @$this->genre; ?></i>, <?php print @$this->tracks; ?> tracks (<?php print @$this->length; ?>)<p>
    <p><?php print @$this->notes; ?></p>
    <p><a class="btn btn-default" href="javascript:loadTracks( <?php print @$this->authorId; ?>, <?php print @$this->id; ?> )" role="button"><span class="glyphicon glyphicon-list"></span> View Tracks</a></p>
    <div id="tracks<?php print @$this->id; ?>"></div>
  </div>

<?php
    if ( $this->counter % 3 == 0 || $this->counter == $this->size - 1 )
    {
?>
</div>
<?php
    }
?>
