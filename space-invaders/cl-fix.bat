REM Please change the path make it relative to your own install base

REM ECHO %PROCESSOR_ARCHITECTURE% | FINDSTR AMD64>NUL && SET VCVARS-CL-FIX=vcvars64 || SET VCVARS-CL-FIX=vcvars32
REM @"%VS100COMNTOOLS%..\..\VC\bin\%VCVARS-CL-FIX%" %1 %2 %3 %4 %5 %6

REM @PATH=%PATH%;%VSINSTALLDIR%\vc\bin;%PROGRAMFILES%\Microsoft SDKs\Windows\v7.0\bin;
REM @PATH=%PATH%;%PROGRAMFILES%\Adobe\Adobe Flash Builder 4.7\sdks\4.6.0\bin;
@PATH=%PATH%;E:\flex_sdk_4.6\bin;
@PATH=%PATH%;%ProgramFiles(x86)%\MSBuild\12.0\bin;./bin/Debug;
