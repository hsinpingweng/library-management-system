$(function() {

    $("a.confirmDeletion").click( function() {
        if (!confirm("Please confirm the deletion.")) return false;
    });
});