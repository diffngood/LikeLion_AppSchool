package com.lion.project2.dao

import com.lion.project2.model.AnimalModel
import java.io.*

class AnimalInfoDAO {
    companion object{
        // 파일 있는지 확인하는 메서드
        fun isExistUserInfoFile():Boolean{
            // File 객체를 생성한다.
            val saveFile = File("userData.dat")
            // 파일이 존재하는지 확인한다.
            // 파일이 있으면 true를 반환하고 없으면 false를 반환한다.
            return saveFile.exists()
        }

        // 파일에서 데이터를 읽어와 ArrayList에 담고 반환한다.
        fun getAnimalInfoList():ArrayList<AnimalModel>?{
            // 파일이 없다면 null을 반환한다.
            if(isExistUserInfoFile() == false){
                return null
            }
            // 파일에서 데이터를 읽어올 기본 스트림을 생성한다.
            val fileInputStream = FileInputStream("userData.dat")
            // 읽어온 데이터를 객체로 복원하는 필터 스트림을 생성한다.
            val objectInputStream = ObjectInputStream(fileInputStream)
            // 읽어온다.
            val resultList = objectInputStream.readObject() as ArrayList<AnimalModel>
            // 파일을 닫아준다.
            objectInputStream.close()
            fileInputStream.close()

            // 객체를 반환한다.
            return resultList
        }

        // 데이터를 파일에 쓴다.
        fun saveAnimalInfoList(dataList:ArrayList<AnimalModel>){
            // 파일에 데이터를 쓰기 위한 기본 스트림을 생성한다.
            // 쓰기를 할 때는 파일이 없을 경우 파일이 생성된다.
            val fileOutputStream = FileOutputStream("userData.dat")
            // 객체를 파일에 쓸 수 있는 형태로 가공하는 필터스트림 생성하여 연결한다.
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            // 객체를 파일에 쓴다.
            objectOutputStream.writeObject(dataList)
            // 파일을 닫아준다.
            objectOutputStream.flush()
            objectOutputStream.close()
            fileOutputStream.close()
        }
    }
}