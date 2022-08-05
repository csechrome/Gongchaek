package com.gongchaek.gongchaek.feature.upload

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.gongchaek.gongchaek.databinding.ActivityUploadBinding
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.model.db.dto.ObjectDetectionResponseDTO
import com.gongchaek.gongchaek.retrofit.ObjectDetectionBuilder
import com.gongchaek.gongchaek.util.showToast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.*
import java.net.URLConnection
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.ArrayList


class UploadActivity : BaseActivity<ActivityUploadBinding>(ActivityUploadBinding::inflate) {

    val REQUEST_IMAGE_CAPTURE = 1
    var listPhoto = ArrayList<Bitmap>()
    var listPhotoPath = ArrayList<String>()
    var listPhotoFile = ArrayList<File>()

    lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(pref.getBoolean("showUploadHint", true)) {
            startActivity(Intent(this, UploadHintActivity::class.java))
        }

        binding.btnAdd.setOnClickListener {
            checkPermission()
            startCapture()
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnNext.setOnClickListener {

            for (i in 0 until listPhoto.size) {

                // todo 1: retrofit 사용
                var requestBody = RequestBody.create(MediaType.parse("image/jpeg"), listPhotoFile[i])
                var body = MultipartBody.Part.createFormData("image", listPhotoFile[i].name, requestBody)

                ObjectDetectionBuilder.api.addImage(body)
                    .enqueue(object : Callback<ObjectDetectionResponseDTO> {
                        override fun onFailure(call: Call<ObjectDetectionResponseDTO>, t: Throwable) {
                            // TODO: 네트워크 연결 상태 확인 로직 추가
                            Log.d(
                                "res", "Failed API call with call: " + call +
                                        " + exception: " + t
                            )
                        }

                        override fun onResponse(call: Call<ObjectDetectionResponseDTO>, response: Response<ObjectDetectionResponseDTO>
                        ) {
                            if (response.isSuccessful) {
                                if (response.code() == 200) {
                                    Log.d("res", response.message())
                                }
                            }
                            Log.d("res", response.code().toString() + " : " + response.message())
                        }
                    })


                // todo 2: 예제 사용
//                val reqStr = StringBuffer()
//                val clientId = "58zycskp7y" //애플리케이션 클라이언트 아이디값";
//                val clientSecret = "OuvYuorGXRCbgjOyGuvhVUjMi9CLf9QMWuwDsPx0" //애플리케이션 클라이언트 시크릿값";

//                Thread {
//                    try {
//                        val paramName = "image" // 파라미터명은 image로 지정
//                        val uploadFile = listPhotoFile[i]
//                        val apiURL = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect" // 객체 인식
//                        val url = URL(apiURL)
//                        val con = url.openConnection() as HttpURLConnection
//                        con.useCaches = false
//                        con.doOutput = true
//                        con.doInput = true
//                        // multipart request
//                        val boundary = "---" + System.currentTimeMillis() + "---"
//                        con.setRequestProperty(
//                            "Content-Type",
//                            "multipart/form-data; boundary=$boundary"
//                        )
//                        con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId)
//                        con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret)
//                        val outputStream = con.outputStream
//                        val writer = PrintWriter(OutputStreamWriter(outputStream, "UTF-8"), true)
//                        val LINE_FEED = "\r\n"
//                        // file 추가
//                        val fileName = uploadFile.name
//                        writer.append("--$boundary").append(LINE_FEED)
//                        writer.append("Content-Disposition: form-data; name=\"$paramName\"; filename=\"$fileName\"")
//                            .append(LINE_FEED)
//                        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName))
//                            .append(LINE_FEED)
//                        writer.append(LINE_FEED)
//                        writer.flush()
//                        val inputStream = FileInputStream(uploadFile)
//                        val buffer = ByteArray(4096)
//                        var bytesRead = -1
//                        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
//                            outputStream.write(buffer, 0, bytesRead)
//                        }
//                        outputStream.flush()
//                        inputStream.close()
//                        writer.append(LINE_FEED).flush()
//                        writer.append("--$boundary--").append(LINE_FEED)
//                        writer.close()
//                        var br: BufferedReader? = null
//                        val responseCode = con.responseCode
//                        br = if (responseCode == 200) { // 정상 호출
//                            BufferedReader(InputStreamReader(con.inputStream))
//                        } else {  // 오류 발생
//                            Log.d("rescode", "error!!!!!!! responseCode= $responseCode")
//                            BufferedReader(InputStreamReader(con.inputStream))
//                        }
//                        Log.d("a", "여기까지 오나?")
//                        var inputLine: String?
//                        if (br != null) {
//                            val response = StringBuffer()
//                            while (br.readLine().also { inputLine = it } != null) {
//                                response.append(inputLine)
//                            }
//                            br.close()
//                            Log.d("response", response.toString())
//                        } else {
//                            Log.d("error", "error !!!")
//                        }
//                    } catch (e: Exception) {
//                        Log.d("e", e.toString())
//                    }
//                }.start()
            }

            // TODO: for listPhoto.size
            // TODO: 조금만 기다리세요. ? 근데 테스트 해보고 봐야 할 듯
            // TODO: object detection
            // TODO: ocr
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("나가기") // TODO: 띵크
            .setMessage("등록하고 있던 책들이 모두 사라져요. 정말 메인 화면으로 돌아갈까요?")
            .setNegativeButton("취소") { dialog, which -> }
            .setPositiveButton("확인") { dialog, which ->
                finish()
            }
            .show()
    }

    private fun checkPermission() {
        var permission = object : PermissionListener {
            override fun onPermissionGranted() {}
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                showToast("서재 등록을 위해 사진 권한을 허용해주세요.")
                checkPermission() // TODO: 이거 어떻게 해야할지..
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            )
            .check()
    }

    private fun startCapture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.gongchaek.gongchaek.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "LIBRARY_PHOTO_",
            ".jpeg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val adapter = RecyclerUploadPhotoAdapter(listPhoto)
        binding.listUpload.adapter = adapter

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            val file = File(currentPhotoPath)
            if (Build.VERSION.SDK_INT < 28) {
                val bitmap = MediaStore.Images.Media
                    .getBitmap(contentResolver, Uri.fromFile(file))
                listPhoto.add(bitmap)

                listPhotoPath.add(currentPhotoPath) // TODO: 코드 정리
                listPhotoFile.add(file)

                adapter.notifyItemInserted(listPhoto.size)
            } else {
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                val bitmap = ImageDecoder.decodeBitmap(decode)
                listPhoto.add(bitmap)

                listPhotoPath.add(currentPhotoPath) //
                listPhotoFile.add(file)

                adapter.notifyItemInserted(listPhoto.size)
            }
        }
    }
}