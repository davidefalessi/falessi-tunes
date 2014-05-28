<h2 class="form-signin-heading">Add a new Track</h2>
<form id="track-add" class="form-horizontal" role="form">

  <div class="form-group">
    <label for="authorId" class="col-sm-2 control-label">Author</label>
    <div class="col-sm-10">
      <select onchange="selectAlbums( this.value );" class="form-control" id="authorId" name="authorId">
        <option></option>
      </select>
    </div>
  </div>

  <div class="form-group">
    <label for="track" class="col-sm-2 control-label">Album</label>
    <div class="col-sm-10">
      <select class="form-control" id="albumId" name="albumId">
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
    <label for="genre" class="col-sm-2 control-label">Genre</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="genre" placeholder="Genre" name="genre">
    </div>
  </div>

  <div class="form-group">
    <label for="length" class="col-sm-2 control-label">Length</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="duration" placeholder="length" name="length">
    </div>
  </div>

  <div class="form-group">
    <label for="producer" class="col-sm-2 control-label">Producer</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="producer" placeholder="Producer" name="producer">
    </div>
  </div>

  <div class="form-group">
    <label for="label" class="col-sm-2 control-label">Label</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="label" placeholder="label" name="label">
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
