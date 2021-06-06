package client;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class LoginSHA {
    // salt생성
    public static String Salt() {
        String salt = "";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            salt = new String(Base64.getEncoder().encode(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }// sha512

    public static String SHA512(String password, String hash) {
        String salt = hash + password;
        String hex = null;
        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-512");
            msg.update(salt.getBytes());
            hex = String.format("%128x", new BigInteger(1, msg.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hex;
    } // test

    public static void main(String[] args) {
    //     Scanner scan = new Scanner(System.in);
    //     int num = 0;
    //     System.out.println("-----회원 가입-----");
    //     System.out.println("id 입력 :");
    //     String id = scan.nextLine();
    //     System.out.println("pw 입력 :");
    //     String pw = scan.nextLine(); // salt값 생성
    //     String salt = Salt();
    //     System.out.println("(salt 생성 완료=" + salt + ")"); // 암호화
    //     String pw_encrypt = SHA512(pw, salt);
    //     System.out.println("(암호화된 비밀번호이자 db에 저장될문자열=" + pw_encrypt + ")");
    //     System.out.println("-----가입 완료-----");
    //     boolean go = true;
    //     System.out.println("-----로 그 인-----");
    //     System.out.println("id 입력 :");
    //     String id_input = scan.nextLine();
    //     while (go) {
    //         if (id.equals(id_input)) {
    //             System.out.println("pw 입력 :");
    //             String pw_input = scan.nextLine(); // 복호화
    //             String pw_decrypt = SHA512(pw_input, salt);
    //             if (pw_decrypt.equals(pw_encrypt)) {
    //                 System.out.println("-----" + id + "님 로그인성공-----");
    //                 go = false;// while문 나가기
    //             } else {
    //                 num++;
    //                 System.out.println("-----비밀번호 틀림. 다시 입력해주세요-----" + num + "회 시도");
    //             }
    //         } else {
    //             System.out.println("---- 없는 아이디 입니다. 다시 입력해주세요");
    //             System.out.println("id 입력:");
    //             id_input = scan.nextLine();
    //         }
    //     }
    // }
}
