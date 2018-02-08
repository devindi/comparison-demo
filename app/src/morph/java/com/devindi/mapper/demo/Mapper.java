package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import net.sf.morph.Morph;
import net.sf.morph.transform.DecoratedConverter;
import net.sf.morph.transform.TransformationException;
import net.sf.morph.transform.Transformer;
import net.sf.morph.transform.converters.IdentityConverter;
import net.sf.morph.transform.copiers.BasePropertyNameCopier;
import net.sf.morph.transform.copiers.PropertyNameMappingCopier;
import net.sf.morph.transform.transformers.BaseTransformer;
import net.sf.morph.transform.transformers.SimpleDelegatingTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Mapper {

    private SimpleDelegatingTransformer graphTransformer;

    public Mapper() {
        graphTransformer = new SimpleDelegatingTransformer();
        UserFieldsCopier userFieldsCopier = new UserFieldsCopier();
        userFieldsCopier.setNestedTransformer(graphTransformer);

        List<Transformer> transformers = new ArrayList<>();
        transformers.add(userFieldsCopier);
        transformers.add(new IdentityConverter());

        Transformer[] transformerArray = transformers.toArray(
                new Transformer[transformers.size()]);
        graphTransformer.setComponents(transformerArray);
    }

    public OrderDto toDto(Order order) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : order.getProducts()) {
            productDtos.add(new ProductDto(product.getTitle()));
        }
        return new OrderDto(
                order.getCustomer().getName(),
                order.getCustomer().getBillingAddress().getCity(),
                order.getCustomer().getBillingAddress().getStreet(),
                order.getCustomer().getShippingAddress().getCity(),
                order.getCustomer().getShippingAddress().getStreet(),
                productDtos
        );
    }

    public PersonDto toPersonDto(Person person) {
        return (PersonDto) Morph.convert(PersonDto.class, person);
    }

    public UserDto toUserDto(Person person) {
        UserDto dto = new UserDto();
        graphTransformer.copy(dto, person);
        return dto;
    }

    public ImmutablePerson toImmutable(Person person) {
        //I didn't found the way to create immutable object with morph
        return new ImmutablePerson(person.getName(), person.getFriends());
    }

    private static class UserFieldsCopier extends PropertyNameMappingCopier {

        private UserFieldsCopier() {
            addMapping("friends", "linked");
            addMapping("name", "name");
        }

        @Override
        protected void copyImpl(Object destination, Object source, Locale locale, Integer preferredTransformationType) throws TransformationException {
            super.copyImpl(destination, source, locale, preferredTransformationType);
        }

        @Override
        protected Class[] getDestinationClassesImpl() throws Exception {
            return new Class[]{UserDto.class};
        }

        @Override
        protected Class[] getSourceClassesImpl() throws Exception {
            return new Class[]{Person.class};
        }
    }
}
