( function modalMessageListenerInitialization ( jQuery ) {

    if ( !jQuery ) {
        return;
    }
    
    jQuery( document ).ready ( function() {
    if ( typeof aem !== 'undefined' && aem.authoring && aem.authoring.command ) {

        aem.authoring.command[ 'harbor-openModal' ] = function( path, message ) {
            console.log( path );
            console.log( message );

            jQuery( '.modal[data-path="' + path + '"]' ).modal( 'show' );
        };

        aem.authoring.command[ 'harbor-closeModal' ] = function( path, message ) {
            console.log( path );
            console.log( message );

            jQuery( '.modal[data-path="' + path + '"]' ).modal( 'hide' );
        };

    }
} ) } )( window.jQuery );