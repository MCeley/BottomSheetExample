# Bottom Sheet Example

This is a pretty quick and dirty example project that shows three different ways in which a developer might create a bottom sheet in Android.

The three situations covered are:

* In-layout with BottomSheetBehavior
* Modal with BottomSheetDialogFragment
* Modal with BottomSheetDialogFragment wrapped in a separate Activity

While the first two cases are likely the most common, the third case can occur if the developer wants to show a bottom sheet for phones and a centered modal for tablets. The Activity would be used to wrap the logic that shows either the bottom sheet or centered modal to simplify using the modal in practice.

* Clicking a season in the list will present an in-layout bottom sheet.
* Clicking sort from the action bar will present a standalone BottomSheetDialogFragment.
* Clicking stats from the action bar will present an Activity that will shows a BottomSheetDialogFragment on phones and a DialogFragment on tablets.