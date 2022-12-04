package com.yoho.blamarket.service;

import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.dto.user.JwtUserDto;
import com.yoho.blamarket.dto.user.RequestEditUserDto;
import com.yoho.blamarket.dto.user.RequestUserRegistDto;
import com.yoho.blamarket.dto.user.ResponseUserDto;
import com.yoho.blamarket.entity.CompanyEntity;
import com.yoho.blamarket.entity.UserEntity;
import com.yoho.blamarket.jwt.JwtProperties;
import com.yoho.blamarket.jwt.JwtUtils;
import com.yoho.blamarket.repository.CompanyRepository;
import com.yoho.blamarket.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;



    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, CompanyRepository companyRepository ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


    @Transactional
    public ApiResponse regist(RequestUserRegistDto userRegistDto){

        ApiResponse apiResponse = null;

        try{
            UserEntity userEntity = userRepository.findByEmail(userRegistDto.getEmail());
            if (userEntity != null){
                log.info("userEntity is null");
                apiResponse = new ApiResponse(400, "회원 가입 실패");
                apiResponse.putData("error", "이미 가입된 회원입니다.");
                return apiResponse;
            }

            // domain, company분리
            String domain = null;
            String company = null;
            try {
                // domail분리 ex) do_o@kakao.com > kakao.com
                domain = userRegistDto.getEmail().split("@")[1];
                // company이름을 분리해준다. ex) kakao.com >> kakao
                log.info("domain : {} ", domain);
                company = domain.split("\\.")[0];
                log.info("company : {} ", company);
            } catch (Exception e){
                log.error("이메일 형식이 아닙니다 : {}", e.toString());
            }


//            왜 아래와 같은 방식으로 구현했냐면... 내가 수정해야하는 테이블이 포링키로 걸려있어서 귀찮아서
            // 도메인 이름 가져와서 체크하고 없을경우 company테이블에 추가
            int company_id = 0;

            try {
                // 도매인 이름으로 가져와서 기존에 등록된 회사가 있는지 없는지 체크
                CompanyEntity companyEntity = companyRepository.findByAccessDomain(domain);
                if (companyEntity != null) {
                    company_id = (int) companyEntity.getId();

                }else{
                    // autoIncreament 사용하려고 했는데 포링키 걸려있어서 가장 최근값 가져와서 넣는과정
                    List<CompanyEntity> companyEntityList =  companyRepository.findTopByOrderByIdDesc();
                    company_id = (int) companyEntityList.get(0).getId() + 1;
                    companyEntity = CompanyEntity.builder()
                            .id(company_id)
                            .accessDomain(domain)
                            .deleteFlag("N")
                            .name(company)
                            .build()
                            ;
                    log.info("companyEntitu {} ", companyEntity.toString());
                    CompanyEntity cEntity = companyRepository.save(companyEntity);
                    log.info("companyEntite {} ", cEntity.toString());
                }
            } catch (Exception e){
                log.error("회사 도메인 로직 에러 : {}", e.toString());
            }


            // dto로 받은 값을 Entity에 전달.
            userEntity = UserEntity.builder()
                    .email(userRegistDto.getEmail())
                    .password(userRegistDto.getPassword())
                    .name(userRegistDto.getName())
                    .company(String.valueOf(company_id))
                    .build()
                    ;
            userEntity.encryptPassword(passwordEncoder);

            // insert
            userEntity = userRepository.save(userEntity);

            // entity그대로 리턴하면 비밀번호도 들어가니까 응답용 dto에 옮김
            ResponseUserDto responseUserRegistDto = ResponseUserDto.builder()
                    .email(userEntity.getEmail())
                    .name(userEntity.getName())
                    .company(userEntity.getCompany())
                    .build()
                    ;

            // api리스폰스를 만들어 리스폰스 규칙을 설정
            apiResponse = new ApiResponse(200, "회원 가입 성공");
            // api리스폰스에 리스폰스 데이터를 설정
            apiResponse.putData("data", responseUserRegistDto);


        } catch (Exception e){
            apiResponse = new ApiResponse(400, "회원 가입 실패");
            log.info("login {}", e.toString());

        }
        return apiResponse;
    }



    public List<UserEntity> getUserList(){
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    public ApiResponse getUser(String email){
        ApiResponse apiResponse = null;
        UserEntity userEntity = userRepository.findByEmail(email);

        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .company(userEntity.getCompany())
                .build()
                ;

        apiResponse = new ApiResponse(200, "회원 조회 성공");
        apiResponse.putData("data", responseUserDto);

        return apiResponse;
    }


    public ApiResponse editUser(RequestEditUserDto requestEditUserDto) {
        ApiResponse apiResponse = null;

        UserEntity userEntity = UserEntity.builder()
                .email(requestEditUserDto.getEmail())
                .password(requestEditUserDto.getPassword())
                .name(requestEditUserDto.getName())
                .company(requestEditUserDto.getCompany())
                .build()
                ;

        try{
            userEntity = userRepository.save(userEntity);
        }catch (Exception e){
            log.error(e.toString());
        }

        return apiResponse;
    }


    public ApiResponse deleteUser(String email) {
        ApiResponse apiResponse = null;

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .build()
                ;

        try{
            userEntity = userRepository.save(userEntity);
        }catch (Exception e){
            log.error(e.toString());
        }

        return apiResponse;
    }

    public ApiResponse refreshJwtToken(HttpServletRequest request, HttpServletResponse response){
        ApiResponse apiResponse = null;
        try {
            String requestToken = request.getHeader(JwtProperties.HEADER_STRING).substring(7);
            String email = JwtUtils.getUserEmail(requestToken);
            JwtUserDto user = JwtUserDto.builder()
                    .email(email)
                    .build()
                    ;
            String token = JwtUtils.createToken(user);
            apiResponse = new ApiResponse(200, "success");
            response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
        } catch (Exception e){
            apiResponse = new ApiResponse(400, "토큰 생성 실패");
            log.error("refresh JWT Token : {} ", e.toString());
        }
        return apiResponse;
    }

    public ApiResponse getUserWithJwtToken(String jwtToken){
        ApiResponse apiResponse = null;
        try {
            String requestToken = jwtToken.substring(7);
            String email = JwtUtils.getUserEmail(requestToken);
            apiResponse = getUser(email);
        } catch (Exception e){
            apiResponse = new ApiResponse(400, "토큰 생성 실패");
            log.error("refresh JWT Token : {} ", e.toString());
        }
        return apiResponse;
    }

//    private Boolean validateDuplicateUser(RequestUserRegistDto userRegistDto) {
//        Optional<UserEntity> optionalUserEntity = Optional.ofNullable(userRepository.findByEmail(userRegistDto.getEmail()));
//        return optionalUserEntity.ifPresent();
//        userEntity.ifPresent(findUser -> {
//            throw new CUserDuplicatedException();
//        });
//    }
}
