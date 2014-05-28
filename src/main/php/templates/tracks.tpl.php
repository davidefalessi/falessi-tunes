<?php
    if ( $this->counter == 0 )
    {
?>
<ol>
<?php
    }
?>

  <li><?php print @$this->name; ?> (<?php print @$this->genre; ?>) - <?php print @$this->notes; ?></li>

<?php
    if ( $this->counter == $this->size - 1 )
    {
?>
</ol>
<?php
    }
?>
