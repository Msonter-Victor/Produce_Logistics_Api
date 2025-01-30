package dev.gagnon.Benue_Produce_Logistics_Api.security.service;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.security.data.models.SecureUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
    private final BioDataRepository userRepository;

    public CustomUserDetailService(BioDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BioData user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new SecureUser(user);
    }
}
