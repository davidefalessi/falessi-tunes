<div id="panel<?php print @$this->id; ?>" class="panel panel-default">
  <div class="panel-heading">
    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#<?php print @$this->id; ?>"><?php print @$this->name; ?></a></h4>
  </div>
  <div id="<?php print @$this->id; ?>" class="panel-collapse collapse" style="height: auto;">
    <div class="panel-body">
      <p>Born on <i><?php print @$this->bornDate; ?></i>, <?php print @$this->gender; ?></p>
      <p>Currently recording for <b><?php print @$this->recordingLabel; ?></b></p>
      <p><i><?php print @$this->notes; ?></i></p>
      <div class="btn-group">
        <button type="button" onclick="javascript:loadAlbums( <?php print @$this->id; ?> )" class="btn btn-primary"><span class="glyphicon glyphicon-list"></span> View Albums</button>
        <button type="button" onclick="javascript:deleteAuthor( <?php print @$this->id; ?>, '<?php print @$this->name; ?>' )" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Delete</button>
      </div>

      <div id="albums<?php print @$this->id; ?>"></div>
    </div>
  </div>
</div>
