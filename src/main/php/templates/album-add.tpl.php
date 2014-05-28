<h2 class="form-signin-heading">Add a new album</h2>
<form id="album-add" class="form-horizontal" role="form">

  <div class="form-group">
    <label for="authorId" class="col-sm-2 control-label">Author</label>
    <div class="col-sm-10">
      <select class="form-control" id="authorId" name="authorId">
        <option></option>
      </select>
    </div>
  </div>

  <div class="form-group">
    <label for="name" class="col-sm-2 control-label">Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" placeholder="Name" name="name">
    </div>
  </div>

  <div class="form-group">
    <label for="publishDate" class="col-sm-2 control-label">Release date</label>
    <div class="col-sm-10">
      <div id="releaseDateGroup" class="input-group date">
        <input disabled="disabled" type="text" class="form-control" id="releaseDate" placeholder="Release Date (yyyy-mm-dd)" name="releaseDate"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
      </div>
    </div>
  </div>

  <div class="form-group">
    <label for="genre" class="col-sm-2 control-label">Genre</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="genre" placeholder="Genre" name="genre">
    </div>
  </div>

  <div class="form-group">
    <label for="hardwareSupport" class="col-sm-2 control-label">Hardware support</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="hardwareSupport" placeholder="Hardware support" name="hardwareSupport">
    </div>
  </div>

  <div class="form-group">
    <label for="producer" class="col-sm-2 control-label">Producer</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="producer" placeholder="Producer" name="producer">
    </div>
  </div>

  <div class="form-group">
    <label for="length" class="col-sm-2 control-label">Duration</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="length" placeholder="Duration" name="length">
    </div>
  </div>

  <div class="form-group">
    <label for="tracks" class="col-sm-2 control-label">Tracks</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="tracks" placeholder="Tracks" name="tracks">
    </div>
  </div>

  <div class="form-group">
    <label for="notes" class="col-sm-2 control-label">Notes</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="notes" name="notes" rows="3"></textarea>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button class="btn btn-lg btn-primary" type="submit">Add</button>
      <button class="btn btn-lg btn-danger" type="reset">Reset</button>
    </div>
  </div>

</form>
