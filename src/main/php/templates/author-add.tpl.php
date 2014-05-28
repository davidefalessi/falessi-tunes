<h2 class="form-signin-heading">Add a new Author</h2>
<form id="author-add" class="form-horizontal" role="form">

  <div class="form-group">
    <label for="name" class="col-sm-2 control-label">Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" placeholder="Name" name="name">
    </div>
  </div>

  <div class="form-group">
    <label for="gender" class="col-sm-2 control-label">Genre</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="gender" placeholder="Genre" name="gender">
    </div>
  </div>

  <div class="form-group">
    <label for="recordingLabel" class="col-sm-2 control-label">Recording Label</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="recordingLabel" placeholder="Recording Label" name="recordingLabel">
    </div>
  </div>

  <div class="form-group">
    <label for="bornDate" class="col-sm-2 control-label">Born date</label>
    <div class="col-sm-10">
      <div id="bornDateGroup" class="input-group date">
        <input disabled="disabled" type="text" class="form-control" id="bornDate" name="bornDate" placeholder="Born Date (yyyy-mm-dd)" name="date"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
      </div>
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
