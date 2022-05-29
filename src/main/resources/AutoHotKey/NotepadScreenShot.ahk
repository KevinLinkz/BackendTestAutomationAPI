#NoEnv 
SetWorkingDir %A_ScriptDir%
#Include Gdip.ahk

path =  % A_ScriptDir "\pathNotepad.txt"
pathResult =  % A_ScriptDir "\ResultAPI.txt"

FileReadLine, line, %path% , 1
file = %line%

run "notepad.exe" %pathResult%

WinActivate, ahk_exe notepad.exe
WinWaitActive,ahk_exe notepad.exe,,2

CoordMode, Pixel,Client

if WinExist("ahk_exe notepad.exe"){


;screen capture
	winget, hwnd
	pToken:=Gdip_Startup()
	;MsgBox, this is hwnd 'n%hwnd%
	pBitmap:=Gdip_BitmapFromHWND(hwnd)
	
	;pBitmap_part:=Gdip_CloneBitmapArea(pBitmap, 0, 0, 100,100) 
	;Gdip_SaveBitmapToFile(pBitmap_part, file)
	
	Gdip_SaveBitmapToFile(pBitmap, file)
	Gdip_DisposeImage(pBitmap)
	
	;Gdip_DisposeImage(pBitmap_part)
	Gdip_Shutdown(pToken)
	
	}else{
	MsgBox, windows doesn't exist
}
WinClose, ahk_class Notepad ;close notepad
exitapp